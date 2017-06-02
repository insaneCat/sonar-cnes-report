
package fr.cnes.sonar.report.providers;

import com.google.gson.JsonObject;
import fr.cnes.sonar.report.exceptions.BadSonarQubeRequestException;
import fr.cnes.sonar.report.exceptions.UnknownParameterException;
import fr.cnes.sonar.report.model.Measure;
import fr.cnes.sonar.report.params.Params;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Provides issue items
 * @author begarco
 */
public class MeasureProvider extends AbstractDataProvider {

    /**
     * Complete constructor
     * @param params Program's parameters
     * @throws UnknownParameterException The program does not recognize the parameter
     */
    public MeasureProvider(Params params) throws UnknownParameterException {
        super(params);
    }

    /**
     * Get all the measures of a project
     * @return Array containing all the measures
     * @throws IOException when contacting the server
     * @throws BadSonarQubeRequestException when the server does not understand the request
     */
    public List<Measure> getMeasures() throws IOException, BadSonarQubeRequestException {
        // results list
        ArrayList<Measure> res = new ArrayList<>();

        // send a request to sonarqube server and return th response as a json object
        // if there is an error on server side this method throws an exception
        JsonObject jo = request(String.format(getRequest("GET_MEASURES_REQUEST"),
                getUrl(), getProjectKey()));

        // put json in a list of measures
        Measure[] tmp = (getGson().fromJson(jo.get("component").getAsJsonObject().get("measures"), Measure[].class));
        // then add all measure to the results list
        res.addAll(Arrays.asList(tmp));

        // return the list
        return res;
    }
}