#include "Error.h"

Error::Error() = default;

Error::Error(const std::string &message): message(message) {
}

std::string Error::getMessage() const {
    return message;
}
