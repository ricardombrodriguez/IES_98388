package com.weather.app;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class App 
{

    private static Logger logger = LogManager.getLogger(App.class.getName());

    public static void main( String[] args )
    {
        
        HashMap<String,Integer> cities = new HashMap<>();

        final String input_city = args[0];

        System.out.println(input_city.toLowerCase());

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        IpmaService service = retrofit.create(IpmaService.class);
        Call<IpmaCityForecast> callSync = service.getCityID();

        try {
            Response<IpmaCityForecast> apiResponse = callSync.execute();
            IpmaCityForecast forecast = apiResponse.body();

            for(CityForecast city : forecast.getData()) {
                String cidade = city.getLocal();
                int id = city.getGlobalIdLocal();
                cities.put(cidade, id);
            }

            System.out.println(cities);

            int city_id = 0;

            for (Map.Entry<String, Integer> entry : cities.entrySet()) {
                if (entry.getKey().equalsIgnoreCase(input_city)) {
                    city_id = entry.getValue();
                    logger.info(entry.getKey() + ":" + entry.getValue());
                    break;
                }
            }

            if (city_id != 0) {

                retrofit = new Retrofit.Builder()
                .baseUrl("http://api.ipma.pt/open-data/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

                service = retrofit.create(IpmaService.class);
                callSync = service.getForecastForACity(city_id);

                try {
                    apiResponse = callSync.execute();
                    forecast = apiResponse.body();

                    logger.info("");
                    logger.info("");
                    logger.info(" === Meteorologia para os próximos cinco dias : " + input_city + " === ");
                    int counter = 1;
                    for(CityForecast meteorologia : forecast.getData()) {
                        String dia = (counter == 1) ? "Hoje" : (counter == 2) ? "Amanhã" : String.valueOf(counter) + "º dia";
                        logger.info("");
                        logger.info(" ================================================ ");
                        logger.info("                        " + dia + "                ");
                        logger.info( "Temperatura mínima: " + meteorologia.getTMin() + "ºC");
                        logger.info( "Temperatura máxima: " + meteorologia.getTMax() + "ºC");
                        logger.info( "Direção vento: " + meteorologia.getPredWindDir());
                        logger.info( "Probabilidade de precipitação: " + meteorologia.getPrecipitaProb() + "%");
                        counter++;
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            } else {
                logger.info(input_city + " é um nome de cidade inválido. Tenta outra vez");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
