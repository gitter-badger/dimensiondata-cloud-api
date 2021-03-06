package com.dimensiondata.cloud.client.http;

import com.dimensiondata.cloud.client.DatacenterService;
import com.dimensiondata.cloud.client.OrderBy;
import com.dimensiondata.cloud.client.Param;
import com.dimensiondata.cloud.client.model.DatacenterType;
import com.dimensiondata.cloud.client.model.Datacenters;

import java.util.Collections;
import java.util.List;

public class DatacenterServiceImpl implements DatacenterService
{
    public static final String PARAMETER_ID = "id";
    public static final List<String> ORDER_BY_PARAMETERS = Collections.unmodifiableList(Collections.singletonList(PARAMETER_ID));

    private final HttpClient httpClient;

    public DatacenterServiceImpl(String baseUrl)
    {
        httpClient = new HttpClient(baseUrl);
    }

    @Override
    public Datacenters listDatacenters(int pageSize, int pageNumber, OrderBy orderBy)
    {
        orderBy.validate(ORDER_BY_PARAMETERS);
        return httpClient.get("infrastructure/datacenter", Datacenters.class,
                new Param(Param.PAGE_SIZE, pageSize),
                new Param(Param.PAGE_NUMBER, pageNumber),
                new Param(Param.ORDER_BY, orderBy.concatenateParameters()));
    }

    @Override
    public DatacenterType getDatacenter(String id)
    {
        return httpClient.get("infrastructure/datacenter/" + id, DatacenterType.class);
    }
}