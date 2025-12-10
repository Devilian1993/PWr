#include "Error.h"

Error::Error() = default;

Error::Error(const std::string &message) {
    this->message = message;
}

std::string Error::getMessage() {
    return message;
}
