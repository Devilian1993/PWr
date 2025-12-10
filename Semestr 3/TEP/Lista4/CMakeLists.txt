cmake_minimum_required(VERSION 4.0)
project(Lista4)

set(CMAKE_CXX_STANDARD 26)

add_executable(Lista4 main.cpp
        Error/Error.cpp
        Error/Error.h
        Result/Result.cpp
        Result/Result.h)
