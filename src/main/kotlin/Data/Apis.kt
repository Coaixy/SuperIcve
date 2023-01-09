package Data

/**
 * 存储API URL
 */
object Apis {
    //个人信息
    const val index = "https://zjy2.icve.com.cn/api/student/Studio/index"
    //课程列表
    const val learningCourseList = "https://zjy2.icve.com.cn/api/student/learning/getLearnningCourseList"
    //进度
    const val processList = "https://zjy2.icve.com.cn/api/study/process/getProcessList"
    //课程列表
    const val stuStudyClassList = "https://zjy2.icve.com.cn/api/common/courseLoad/getStuStudyClassList"
    //Topic
    const val topicByModuleId = "https://zjy2.icve.com.cn/api/study/process/getTopicByModuleId"
    //数据上传
    const val stuProcessCellLog = "https://zjy2.icve.com.cn/api/common/Directory/stuProcessCellLog"
    //获取CellID和LogCellID
    const val viewDirectory = "https://zjy2.icve.com.cn/api/common/Directory/viewDirectory"
    //获取Cell Info
    const val cellByTopicId = "https://zjy2.icve.com.cn/api/study/process/getCellByTopicId"
}