package com.example.iot.configuration;

import org.jetbrains.annotations.NotNull;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.converter.MessageConverter;
import org.springframework.messaging.handler.invocation.HandlerMethodArgumentResolver;
import org.springframework.messaging.handler.invocation.HandlerMethodReturnValueHandler;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

import java.util.List;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void registerStompEndpoints(StompEndpointRegistry stompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/websocket-example").setAllowedOrigins("https://www.watertec.tecsar.net", "http://localhost:4200")
                .withSockJS();
    }

    @Override
    public void configureWebSocketTransport(@NotNull WebSocketTransportRegistration webSocketTransportRegistration) {

    }

    @Override
    public void configureClientInboundChannel(@NotNull ChannelRegistration channelRegistration) {

    }

    @Override
    public void configureClientOutboundChannel(@NotNull ChannelRegistration channelRegistration) {

    }

    @Override
    public void addArgumentResolvers(@NotNull List<HandlerMethodArgumentResolver> list) {

    }

    @Override
    public void addReturnValueHandlers(@NotNull List<HandlerMethodReturnValueHandler> list) {

    }

    @Override
    public boolean configureMessageConverters(@NotNull List<MessageConverter> list) {
        return true;
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {
        registry.enableSimpleBroker("/topic");
        registry.setApplicationDestinationPrefixes("/app");
    }
}
