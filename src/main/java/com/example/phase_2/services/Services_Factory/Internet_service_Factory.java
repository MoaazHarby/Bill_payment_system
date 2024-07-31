package com.example.phase_2.services.Services_Factory;

import com.example.phase_2.services.Internet.*;
import com.example.phase_2.services.Mobile.*;

public class Internet_service_Factory implements Service_Factory{

    public Internet_service_Factory() {}

    public Internet_Service select_service_provider(String provider)
    {
        provider=provider.toLowerCase();
        Internet_Service service;

        if (provider.equals("we"))
        {
            service=new We_Internet();
        }
        else if (provider.equals("vodafone"))
        {
            service=new Vodafone_Internet();
        }
        else if (provider.equals("orange"))
        {
            service=new Orange_Internet();
        }
        else if (provider.equals("etisalat"))
        {
            service=new Etisalat_Internet();
        }
        else
        {
            return null;
        }

        return service;
    }


}
