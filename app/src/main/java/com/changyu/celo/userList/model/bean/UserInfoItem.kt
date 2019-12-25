package com.changyu.celo.userList.model.bean

/*
api.age      = random.numeric(1, 25);
api.dogYears = api.age * 7;
api.name    = faker.name.findName();
api.gender = list(["male", "female"]);
api.title = list(["Mr." , "Mrs." , "Dr."] )
api.dateOfBirth = timestamp()-random.numeric(20*365*24*60*60, 60*365*24*60*60);

* */
data class UserInfoItem(
    val info: Info,
    val results: List<Result>
)

data class Info(
    val page: String,
    val results: String,
    val seed: String,
    val time: Time,
    val version: String
)

data class Time(
    val generate: Int,
    val instruct: Int
)

data class Result(
    val age: Int,
    val dateOfBirth: Int,
    val gender: String,
    val id: Long,
    val image: String,
    val name: String,
    val title: String,
    val detail: String
)