// ==UserScript==
// @name         SuperIcve
// @namespace    http://www.nakpump.top
// @include      https://www.zjy2.icve.com.cn/study/homework/do.html*
// @include      https://zjy2icve.com.cn/study/onlineExam/*
// @version      1.0.0
// @description  仅供复习，请勿用于非法用途
// @author       Coaixy
// @grant        none
// ==/UserScript==


//基础信息配置
const HOST = "127.0.0.1"
const PORT = "1109"
const websocketUrl = "ws://"+HOST+":"+PORT

//函数

function log(str){
    console.log("【SuperIcve】"+str)
}

//主要功能区
let ws = new WebSocket(websocketUrl)

ws.onopen = function (event){
    if (ws.readyState === WebSocket.OPEN){
        log("连接成功")
    }
}
ws.onmessage = function (event){
    let data = event.data
    let jsonData = JSON.parse(data)

}