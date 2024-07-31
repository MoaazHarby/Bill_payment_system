package com.example.phase_2.services.Services_Factory;

import com.example.phase_2.services.Donation.Cancer_Donation_Service;
import com.example.phase_2.services.Donation.Donation_Service;
import com.example.phase_2.services.Donation.NGO_Donation_Service;
import com.example.phase_2.services.Donation.Schools_Donation_Service;
import com.example.phase_2.services.Mobile.*;
import com.example.phase_2.services.Service;

public class Mobile_service_Factory implements Service_Factory {

    public Mobile_service_Factory()
    {
    }

    public Mobile_service select_service_provider(String provider)
    {
        provider=provider.toLowerCase();
        Mobile_service service;

        if (provider.equals("we"))
        {
            service=new We_Mobile();
        }
        else if (provider.equals("vodafone"))
        {
            service=new Vodafone_Mobile();
        }
        else if (provider.equals("orange"))
        {
            service=new Orange_Mobile();
        }
        else if (provider.equals("etisalat"))
        {
            service=new Etisalat_Mobile();
        }
        else
        {
            return null;
        }

        return service;
    }
}
