package com.example.phase_2.services.Services_Factory;

import com.example.phase_2.services.Donation.Cancer_Donation_Service;
import com.example.phase_2.services.Donation.Donation_Service;
import com.example.phase_2.services.Donation.NGO_Donation_Service;
import com.example.phase_2.services.Donation.Schools_Donation_Service;
import com.example.phase_2.services.Landline.Landline_Service;
import com.example.phase_2.services.Landline.Monthly_LandLine_Service;
import com.example.phase_2.services.Landline.Quarterly_LandLine_Service;

public class Landline_service_Factory implements Service_Factory{

    public Landline_service_Factory()
    {
    }

    public Landline_Service select_landline_service(String payment_type)
    {
        payment_type=payment_type.toLowerCase();
        Landline_Service service;

        if (payment_type.equals("monthly")|| payment_type.equals("month") )
        {
            service=new Monthly_LandLine_Service();
        }
        else if (payment_type.equals("quarterly")||payment_type.equals("quarter"))
        {
            service=new Quarterly_LandLine_Service();
        }
        else
        {
            return null;
        }

        return service;

    }
}
