#ifndef LISTA4_ERROR_H
#define LISTA4_ERROR_H
#include <string>


class Error {
private:
    std::string message;
public:
    Error();
    Error(const std::string& message);
    std::string getMessage();
public:
};


#endif //LISTA4_ERROR_H