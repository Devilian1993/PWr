package com.example.diskschedulingsimulation.generator;

import com.example.diskschedulingsimulation.model.DiskRequest;

import java.util.ArrayList;

public interface Generator {
    ArrayList<DiskRequest> generate();
}
