// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package br.ufpb.ccae.dcx.lcc.tcc.controllers;

import br.ufpb.ccae.dcx.lcc.tcc.controllers.ApplicationConversionServiceFactoryBean;
import br.ufpb.ccae.dcx.lcc.tcc.model.Answer;
import br.ufpb.ccae.dcx.lcc.tcc.model.Challenge;
import br.ufpb.ccae.dcx.lcc.tcc.model.Location;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;

privileged aspect ApplicationConversionServiceFactoryBean_Roo_ConversionService {
    
    declare @type: ApplicationConversionServiceFactoryBean: @Configurable;
    
    public Converter<Answer, String> ApplicationConversionServiceFactoryBean.getAnswerToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.ufpb.ccae.dcx.lcc.tcc.model.Answer, java.lang.String>() {
            public String convert(Answer answer) {
                return new StringBuilder().append(answer.getDescription()).toString();
            }
        };
    }
    
    public Converter<Long, Answer> ApplicationConversionServiceFactoryBean.getIdToAnswerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.ufpb.ccae.dcx.lcc.tcc.model.Answer>() {
            public br.ufpb.ccae.dcx.lcc.tcc.model.Answer convert(java.lang.Long id) {
                return Answer.findAnswer(id);
            }
        };
    }
    
    public Converter<String, Answer> ApplicationConversionServiceFactoryBean.getStringToAnswerConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.ufpb.ccae.dcx.lcc.tcc.model.Answer>() {
            public br.ufpb.ccae.dcx.lcc.tcc.model.Answer convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Answer.class);
            }
        };
    }
    
    public Converter<Challenge, String> ApplicationConversionServiceFactoryBean.getChallengeToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.ufpb.ccae.dcx.lcc.tcc.model.Challenge, java.lang.String>() {
            public String convert(Challenge challenge) {
                return new StringBuilder().append(challenge.getDescription()).append(' ').append(challenge.getDifficulty()).toString();
            }
        };
    }
    
    public Converter<Long, Challenge> ApplicationConversionServiceFactoryBean.getIdToChallengeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.ufpb.ccae.dcx.lcc.tcc.model.Challenge>() {
            public br.ufpb.ccae.dcx.lcc.tcc.model.Challenge convert(java.lang.Long id) {
                return Challenge.findChallenge(id);
            }
        };
    }
    
    public Converter<String, Challenge> ApplicationConversionServiceFactoryBean.getStringToChallengeConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.ufpb.ccae.dcx.lcc.tcc.model.Challenge>() {
            public br.ufpb.ccae.dcx.lcc.tcc.model.Challenge convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Challenge.class);
            }
        };
    }
    
    public Converter<Location, String> ApplicationConversionServiceFactoryBean.getLocationToStringConverter() {
        return new org.springframework.core.convert.converter.Converter<br.ufpb.ccae.dcx.lcc.tcc.model.Location, java.lang.String>() {
            public String convert(Location location) {
                return new StringBuilder().append(location.getDescription()).append(' ').append(location.getLatitude()).append(' ').append(location.getLongitude()).append(' ').append(location.getRadius()).toString();
            }
        };
    }
    
    public Converter<Long, Location> ApplicationConversionServiceFactoryBean.getIdToLocationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.Long, br.ufpb.ccae.dcx.lcc.tcc.model.Location>() {
            public br.ufpb.ccae.dcx.lcc.tcc.model.Location convert(java.lang.Long id) {
                return Location.findLocation(id);
            }
        };
    }
    
    public Converter<String, Location> ApplicationConversionServiceFactoryBean.getStringToLocationConverter() {
        return new org.springframework.core.convert.converter.Converter<java.lang.String, br.ufpb.ccae.dcx.lcc.tcc.model.Location>() {
            public br.ufpb.ccae.dcx.lcc.tcc.model.Location convert(String id) {
                return getObject().convert(getObject().convert(id, Long.class), Location.class);
            }
        };
    }
    
    public void ApplicationConversionServiceFactoryBean.installLabelConverters(FormatterRegistry registry) {
        registry.addConverter(getAnswerToStringConverter());
        registry.addConverter(getIdToAnswerConverter());
        registry.addConverter(getStringToAnswerConverter());
        registry.addConverter(getChallengeToStringConverter());
        registry.addConverter(getIdToChallengeConverter());
        registry.addConverter(getStringToChallengeConverter());
        registry.addConverter(getLocationToStringConverter());
        registry.addConverter(getIdToLocationConverter());
        registry.addConverter(getStringToLocationConverter());
    }
    
    public void ApplicationConversionServiceFactoryBean.afterPropertiesSet() {
        super.afterPropertiesSet();
        installLabelConverters(getObject());
    }
    
}
