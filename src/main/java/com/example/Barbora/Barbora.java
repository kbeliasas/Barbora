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

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
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
                log.info("Checking " + matrix.day);
                matrix.hours.forEach(hour -> {
                    if (hour.available) {
                        log.info("Day = " + matrix.day);
                        log.info("Hour = " + hour.hour);
                        log.info("Price = " + hour.price);
                        list.add(new BarboraTimeModel(matrix.day, hour.hour, hour.price));
                    }
                });
            });
        });

        if (!list.isEmpty()) {
            senderService.Send(list);
        }
    }
}
