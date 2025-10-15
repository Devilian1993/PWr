cmake_minimum_required(VERSION 4.0)
project(Lab1)

set(CMAKE_CXX_STANDARD 23)

add_executable(Lab1 main.cpp
        allocation.cpp
        allocation.h
        Table/Table.cpp
        Table/Table.h)
