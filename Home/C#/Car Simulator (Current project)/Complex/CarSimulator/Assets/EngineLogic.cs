using System;
using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class EngineLogic : MonoBehaviour
{
    // Variables influenced by the player:
    // Displacement and Rods
    double bore;
    double stroke;
    double rodLength;

    // Cylinder Amount
    int cylinderAmount;

    // RPM related
    int redline;
    int revLimit;


    // Variables calculated from player's values:
    // Statistics
    double peakHP;
    double averageHP;
    double peakTorque;
    double averageTorque;

    // RPM related
    int currentRPM;
    double forceAppliedToLever;

    // Displacement related
    double displacement;

    // Throttle related
    double throttleInput;

    // Important numbers for physics
    double currentTorque;
    double angularVelocity;
    double angularAcceleration;
    double inertia;
    double pressure;
    double mass;

    // Start is called before the first frame update
    void Start()
    {
        // The force applied to the leverarm is equal to pressure times area (bore)
        forceAppliedToLever = pressure * (Math.PI * Math.Pow(bore / 2, 2));

        // The current torque is equal to the force times the radious of the lever
        currentTorque = forceAppliedToLever * rodLength;

        // Inertia is equal to 
        inertia = mass * Math.Pow(rodLength, 2);

        // Angular acceleration is equal to the current torque devided by inertia (In radians/s^2)
        angularAcceleration = currentTorque / inertia;

        // Current RPM is equal to...

        // Angular velocity is equal to angualar acceleration times time (May need an update later, as this application
        // assumes a constant acceleration)


        // Reverse engineered pressure is equal to torque devided by lever radius (both together in NM) devided by area in m^2
        pressure = currentTorque / rodLength / Math.Pow(Math.PI * (bore / 2), 2);



        // Test Area------------------------------------------------------------------------------------------------------------

        // Variables are set
        bore = 86 / 1000; // Devided by 1000 to match m^2 instead of mm^2
        currentTorque = 293; // In NM
        rodLength = 121.5 / 1000; // Same as above in meters

        // Reverse engineered pressure formula applied
        pressure = currentTorque / rodLength / (Math.PI * Math.Pow(bore / 2, 2));

        // Show me the result
        Debug.Log(pressure + " in NM");
    }

    // FixedUpdate is mainly used for physics
    void FixedUpdate()
    {
        
    }
}
