package com.example.Barbora;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.example.Barbora.models.BarboraApiModels.BarboraApiModel;
import com.example.Barbora.models.BarboraTimeModel;
import com.example.Barbora.services.RestService;
import com.example.Barbora.services.SenderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Barbora {

    @Autowired
    SenderService senderService;
    @Autowired
    RestService restService;


    //@Scheduled(cron = "15 */15 7-23 * * *")
    @Scheduled(cron = "*/30 * * * * *")
    void availabilityUsingAPI() {

        BarboraApiModel barboraApiModel = restService.getBarboraTimes();

        List<BarboraTimeModel> list = new ArrayList<>();

        barboraApiModel.deliveries.forEach(delivery -> {
            delivery.params.matrix.forEach(matrix -> {
                System.out.println("Now = " + LocalDateTime.now());
                System.out.println("Checking " + matrix.day);
                matrix.hours.forEach(hour -> {
                    if (hour.available) {
                        System.out.println("Day = " + matrix.day);
                        System.out.println("Hour = " + hour.hour);
                        System.out.println("Price = " + hour.price);
                        System.out.println();
                        list.add(new BarboraTimeModel(matrix.day, hour.hour, hour.price));
                    }
                });
            });
        });

        if (!list.isEmpty()) {
            senderService.Send(list);
        }
        System.out.println();
    }
}
