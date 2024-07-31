package com.example.phase_2.services.Services_Factory;

import com.example.phase_2.services.Donation.*;

//donation
//ngo
//cancer
//school


public class Donation_service_Factory implements Service_Factory{

    public Donation_service_Factory()
    {
    }

    public Donation_Service select_donation_service(String provider)
    {
        provider=provider.toLowerCase();
        Donation_Service service;

        if (provider.equals("cancer"))
        {
            service=new Cancer_Donation_Service();
        }
        else if (provider.equals("ngo"))
        {
            service=new NGO_Donation_Service();
        }
        else if (provider.equals("school") || provider.equals("schools"))
        {
            service=new Schools_Donation_Service();
        }
        else
        {
            return null;
        }

        return service;

    }
}
