package com.changyu.celo.service.exception

import java.io.IOException

class HttpResultException(val code: Int, val msg: String) : IOException()