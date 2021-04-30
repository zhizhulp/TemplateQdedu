package template.qdedu_module.java.debug.arouter.service

import util.timeNow
import java.util.*

fun dataCenter()="""
    package debug.arouter.service

    import android.content.Context
    import com.alibaba.android.arouter.facade.annotation.Route
    import com.qdedu.baselibcommon.arouter.BaseRouterHub
    import com.qdedu.baselibcommon.arouter.service.IDataCenterService

    /**
     * description: 数据中心
     * author: liping
     * date: ${Date().timeNow()}
     */
    @Route(path = BaseRouterHub.ROUTE_DATA_CENTER, name = "数据提供服务")
    class DataCenterServiceImpl : IDataCenterService {
        override fun userId() = "82693749120434176"

        override fun userGrade(): String {
            return ""
        }

        override fun userGender(): String {
            return ""
        }

        override fun appToken(): String {
            return "92b6eddf61f3417c8daa7ddba22c1f3c"
        }

        override fun userInfo(): String {
            return ""
        }

        override fun userGradeInfo(): String {
            return ""
        }

        override fun hostMain(): String {
            return ""
        }

        override fun schoolId(): Long {
            return -1
        }

        override fun roleId(): Int {
            return -1
        }

        override fun userClassIdList(): List<Long> {
            TODO("Not yet implemented")
        }

        override fun init(context: Context?) {
        }

    }
""".trimIndent()