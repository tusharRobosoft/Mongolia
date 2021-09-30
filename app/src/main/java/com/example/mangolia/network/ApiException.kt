package com.example.mangolia.network

import com.example.mangolia.utils.Constants
import java.io.IOException

class ApiException(code: Int? = Constants.NetworkConstants.DEFAULT_ERROR_CODE, message: String?) :
    IOException(message)