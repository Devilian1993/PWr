cmake_minimum_required(VERSION 4.0)
project(Lista4)

set(CMAKE_CXX_STANDARD 26)

add_executable(Lista4 main.cpp
        Error/Error.cpp
        Error/Error.h
        Result/Result.cpp
        Result/Result.h
        Tree/Tree.cpp
        Tree/Node/Node.cpp
        Utils/Utils.cpp
        Console/Console.cpp
        ResultSaver/ResultSaver.cpp
        ResultSaver/ResultSaver.h)
