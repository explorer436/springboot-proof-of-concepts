package poc.module.referenceData;

import com.lmig.pm.domain.common.layers.service.referencedata.v1_0.referencedataload.ReferenceDataLoad_Service;
import com.lmig.pm.domain.common.types.v1_0.SystemInformationType;
import com.lmig.pm.domain.common.types.v1_0.VersionInformationType;
import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableRequest;
import com.lmig.pm.domain.common.layers.service.messages.getreferencedatatable.v1_0.GetReferenceDataTableResponse;
import com.lmig.pm.domain.common.layers.service.referencedata.v1_0.referencedataload.ReferenceDataLoad;

import org.springframework.stereotype.Repository;

import poc.shared.Environment;


@Repository
public class ReferenceDataRepository {

    public GetReferenceDataTableResponse getReferenceDataTable(Environment env) {
        ReferenceDataLoad_Service service = new ReferenceDataLoad_Service(env.getURL());
        ReferenceDataLoad server = service.getReferenceDataLoadSOAP();
        GetReferenceDataTableRequest request = new GetReferenceDataTableRequest();

        SystemInformationType clientInformation = new SystemInformationType();
        clientInformation.setEnvironment(env.getEnvironment()); 
        clientInformation.setApplicationName("ReferenceDataClient");
        clientInformation.setApplicationType("online");
        clientInformation.setApplicationVersion("1.0");

        VersionInformationType version = new VersionInformationType();
        version.setMajor(1);
        version.setMinor(0);

        request.setExecutionContext("fully-integrated-mode");
        request.setReleaseContext(env.getReleaseContext()); 
        request.setTableName("ACORD_THIRD_PARTY_COMPANIES");
        request.setIsError(false);
        request.setVersionInformation(version);
        request.setClientInformation(clientInformation);

        GetReferenceDataTableResponse response = server.getReferenceDataTable(request);

        return response;
    }
}