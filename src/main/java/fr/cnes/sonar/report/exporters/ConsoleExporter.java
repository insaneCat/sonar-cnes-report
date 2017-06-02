package fr.cnes.sonar.report.exporters;

import fr.cnes.sonar.report.exceptions.BadExportationDataTypeException;
import fr.cnes.sonar.report.model.Issue;
import fr.cnes.sonar.report.model.Report;
import fr.cnes.sonar.report.params.Params;
import fr.cnes.sonar.report.params.ParamsFactory;

import java.util.List;
import java.util.logging.Logger;

/**
 * Exports the report in the console
 * @author begarco
 */
public class ConsoleExporter implements IExporter {

    /**
     * logger
     */
    private static final Logger LOGGER = Logger.getLogger(ParamsFactory.class.getCanonicalName());

    /**
     * Overridden export for console
     * @param data Data to export as report
     * @param params Program's parameters
     * @param filename Name of the file to export
     * @throws BadExportationDataTypeException data is not a Report
     */
    @Override
    public void export(Object data, Params params, String filename)
            throws BadExportationDataTypeException {
        // check data type
        if(!(data instanceof Report)) {
            throw new BadExportationDataTypeException();
        }
        // data casting
        Report report = (Report) data;

        // get issues
        List<Issue> issues = report.getIssues();
        LOGGER.info("key\t" + "project\t" + "component\t" + "type\t" + "severity\t" +
                "message\t" + "line\t" + "status\t" + "\t");
        // log all issues
        for(Issue issue : issues) {
            LOGGER.info(issue.toString());
        }
        // log number of issues
        LOGGER.info("Nombre total de violations : " + String.valueOf(issues.size()));
    }
}