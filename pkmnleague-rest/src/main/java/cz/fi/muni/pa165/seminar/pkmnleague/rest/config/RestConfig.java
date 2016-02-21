package cz.fi.muni.pa165.seminar.pkmnleague.rest.config;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.BadgeDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.PokemonDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.dto.TrainerDTO;
import cz.fi.muni.pa165.seminar.pkmnleague.rest.mixins.BadgeDTOMixin;
import cz.fi.muni.pa165.seminar.pkmnleague.rest.mixins.PokemonDTOMixin;
import cz.fi.muni.pa165.seminar.pkmnleague.rest.mixins.TrainerDTOMixin;
import cz.fi.muni.pa165.seminar.pkmnleague.sampledata.SampleDataConfig;
import org.springframework.context.annotation.*;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.text.SimpleDateFormat;
import java.util.List;

@EnableWebMvc
@Configuration
@Import({SampleDataConfig.class})
@ComponentScan(basePackages = "cz.fi.muni.pa165.seminar.pkmnleague.rest.controllers")
public class RestConfig extends WebMvcConfigurerAdapter {

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }

    @Bean
    @Primary
    public MappingJackson2HttpMessageConverter customJackson2HttpMessageConverter() {
        MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));

        objectMapper.disable(MapperFeature.DEFAULT_VIEW_INCLUSION);

        objectMapper.addMixIn(PokemonDTO.class, PokemonDTOMixin.class);
        objectMapper.addMixIn(BadgeDTO.class, BadgeDTOMixin.class);
        objectMapper.addMixIn(TrainerDTO.class, TrainerDTOMixin.class);

        jsonConverter.setObjectMapper(objectMapper);
        return jsonConverter;
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(customJackson2HttpMessageConverter());
    }

}