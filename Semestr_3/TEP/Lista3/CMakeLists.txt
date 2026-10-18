cmake_minimum_required(VERSION 4.0)
project(Lista3)

set(CMAKE_CXX_STANDARD 20)

add_executable(Lista3 main.cpp
        Utils/Utils.cpp
        Utils/Utils.h
        Tree/Node/Node.cpp
        Tree/Node/Node.h
        Tree/Tree.cpp
        Tree/Tree.h
        Console/Console.cpp
        Console/Console.h)
