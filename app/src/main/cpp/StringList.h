//
// Created by Дмитрий Исаев on 23.10.2024.
//

#ifndef LAB8_STRINGLIST_H
#define LAB8_STRINGLIST_H

#include <vector>
#include <string>
#include <sstream>
#include <algorithm>

class StringList {
private:
    std::vector<std::string> strings;

public:
    void addString(const std::string& str) {
        std::string lowerStr = str;
        std::transform(lowerStr.begin(), lowerStr.end(), lowerStr.begin(), ::tolower);
        strings.push_back(lowerStr);
    }

    void removeLast() {
        if (!strings.empty()) {
            strings.pop_back();
        }
    }

    std::string getAllStringsAsCommaSeparated() const {
        if (strings.empty()) {
            return "";
        }

        std::ostringstream oss;
        for (size_t i = 0; i < strings.size(); ++i) {
            if (i != 0) {
                oss << ", ";
            }
            oss << strings[i];
        }

        std::string result = oss.str();
        if (!result.empty()) {
            result[0] = toupper(result[0]);
        }

        return result;
    }

    const std::vector<std::string>& getStrings() const {
        return strings;
    }

    size_t size() const {
        return strings.size();
    }
};

#endif //LAB8_STRINGLIST_H
