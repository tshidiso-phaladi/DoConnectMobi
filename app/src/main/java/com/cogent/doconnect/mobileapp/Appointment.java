package com.cogent.doconnect.mobileapp;

/**
 * Created by Thabane.N on 2016/10/21.
 */

public class Appointment {
    private int Appointment_ID;
    private boolean Appointments_App_Status;
    private String Appointments_Date_Time;
    private String Appointments_Details;
    private int Patient_ID;
    private String Patient_FirstName;
    private String Patient_LastName;
    private String Patient_Cell_Number;
    private String Patient_Email;
    private String Doctors_Email;
    private String Doctors_FirstName;
    private String Doctors_LastName;
    private int Doctors_ID;
    private String Doctors_Job_Title;
    private int Practice_ID;
    private String Practice_Name;
    private String Practice_Phone_Number;
    private String Practice_Fax_Number;
    private String Practice_Address;

    public Appointment()
    {

    }

    //***************************************************
    public int GetAppointment_ID()
    {
        return Appointment_ID;
    }
    public void SetAppointment_ID(int appointments_ID)
    {
        Appointment_ID = appointments_ID;
    }
    //***************************************************
    public boolean GetAppointments_App_Status()
    {
        return Appointments_App_Status;
    }

    public void SetAppointments_App_Status(boolean appointments_App_Status)
    {
        Appointments_App_Status = appointments_App_Status;
    }
    //***************************************************
    public String GetAppointments_Date_Time()
    {
        return Appointments_Date_Time;
    }
    public void SetAppointments_Date_Time(String appointments_Date_Time)
    {
        Appointments_Date_Time = appointments_Date_Time;
    }
    //***************************************************
    public String GetAppointments_Details()
    {
        return Appointments_Details;
    }
    public void SetAppointments_Details(String appointments_Details)
    {
        Appointments_Details = appointments_Details;
    }
    //***************************************************
    public int GetPatient_ID()
    {
        return Patient_ID;
    }
    public void SetPatient_ID(int patient_ID)
    {
        Patient_ID = patient_ID;
    }
    //***************************************************
    public String GetPatient_FirstName()
    {
        return Patient_FirstName;
    }
    public void SetPatient_FirstName(String patient_FirstName)
    {
        Patient_FirstName = patient_FirstName;
    }
    //***************************************************
    public String GetPatient_LastName()
    {
        return Patient_LastName;
    }
    public void SetPatient_LastName(String patient_LastName)
    {
        Patient_LastName = patient_LastName;
    }
    //***************************************************
    public String GetPatient_Cell_Number()
    {
        return Patient_Cell_Number;
    }
    public void SetPatient_Cell_Number(String patient_Cell_Number)
    {
        Patient_Cell_Number = patient_Cell_Number;
    }
    //***************************************************
    public String GetPatient_Email()
    {
        return Patient_Email;
    }
    public void SetPatient_Email(String patient_Email)
    {
        Patient_Email = patient_Email;
    }
    //***************************************************
    public String GetDoctors_Email()
    {
        return Doctors_Email;
    }
    public void SetDoctors_Email(String doctors_Email)
    {
        Doctors_Email = doctors_Email;
    }
    //***************************************************
    public String GetDoctors_FirstName()
    {
        return Doctors_FirstName;
    }
    public void SetDoctors_FirstName(String doctors_FirstName)
    {
        Doctors_FirstName = doctors_FirstName;
    }
    //***************************************************
    public String GetDoctors_LastName()
    {
        return Doctors_LastName;
    }
    public void SetDoctors_LastName(String doctors_LastName)
    {
        Doctors_LastName = doctors_LastName;
    }
    //***************************************************
    public int GetDoctors_ID()
    {
        return Doctors_ID;
    }
    public void SetDoctors_ID(int doctors_ID)
    {
        Doctors_ID = doctors_ID;
    }
    //***************************************************
    public String GetDoctors_Job_Title()
    {
        return Doctors_Job_Title;
    }
    public void SetDoctors_Job_Title(String doctors_Job_Title)
    {
        Doctors_Job_Title = doctors_Job_Title;
    }
    //***************************************************
    public int GetPractice_ID()
    {
        return Practice_ID;
    }
    public void SetPractice_ID(int practice_ID)
    {
        Practice_ID = practice_ID;
    }
    //***************************************************
    public String GetPractice_Phone_Number()
    {
        return Practice_Phone_Number;
    }
    public void SetPractice_Phone_Number(String practice_Phone_Number)
    {
        Practice_Phone_Number = practice_Phone_Number;
    }
    //***************************************************
    public String GetPractice_Name()
    {
        return Practice_Name;
    }
    public void SetPractice_Name(String practice_Name)
    {
        Practice_Name = practice_Name;
    }
    //***************************************************
    public String GetPractice_Fax_Number()
    {
        return Practice_Fax_Number;
    }
    public void SetPractice_Fax_Number(String practice_Fax_Number)
    {
        Practice_Fax_Number = practice_Fax_Number;
    }
    //***************************************************
    public String GetPractice_Address()
    {
        return Practice_Address;
    }
    public void SetDPractice_Address(String practice_Address)
    {
        Practice_Address = practice_Address;
    }
    //***************************************************
}

