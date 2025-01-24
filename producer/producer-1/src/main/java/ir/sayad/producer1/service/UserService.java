package ir.sayad.producer1.service;

import ir.sayad.lib.common.event.UserCreateEvent;
import ir.sayad.lib.common.payload.User;
import ir.sayad.lib.common.util.JsonUtil;
import ir.sayad.lib.common.util.Logger;
import ir.sayad.producer1.config.Config;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

import static ir.sayad.lib.common.util.JsonUtil.plainToClass;


@Service
public class UserService {
    private final KafkaTemplate<String, UserCreateEvent> TEMPLATE;
    private final Config CONFIG;

    public UserService(KafkaTemplate<String, UserCreateEvent> kafkaTemplate, Config config) {
        TEMPLATE = kafkaTemplate;
        CONFIG = config;
    }

    public User add(User user) {
        user.setId(UUID.randomUUID().toString());
        UserCreateEvent event = plainToClass(user, UserCreateEvent.class);
        Logger.info("UserCreateEvent::send", event);

        CompletableFuture<SendResult<String, UserCreateEvent>> send = TEMPLATE.send(CONFIG.getTopicName(), user.getId(), event);         // 👉 Consider using join() for synchronous send

        send.whenComplete((result, exception) -> {
            if (ObjectUtils.isEmpty(exception))
                Logger.info("UserCreateEvent::send-success", Map.of(
                        "topic", result.getRecordMetadata().topic(),
                        "partition", result.getRecordMetadata().partition(),
                        "offset", result.getRecordMetadata().offset()
                ));
            else
                Logger.error("UserCreateEvent::send-error", exception.getMessage());
        });

        // SYNC MODE

        // TODO sayad  save to db
        return user;
    }

}
