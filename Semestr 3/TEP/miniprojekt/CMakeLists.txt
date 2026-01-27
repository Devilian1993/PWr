cmake_minimum_required(VERSION 4.0)
project(miniprojekt)

set(CMAKE_CXX_STANDARD 14)

add_executable(miniprojekt main.cpp
        Evaluator.h
        Evaluator.cpp
        Individual.cpp
        Individual.h
        GeneticAlgorithm.cpp
        GeneticAlgorithm.h)

file(COPY data DESTINATION ${CMAKE_CURRENT_BINARY_DIR})
