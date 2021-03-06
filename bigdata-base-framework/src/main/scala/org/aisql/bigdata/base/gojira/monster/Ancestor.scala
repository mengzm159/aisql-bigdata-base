package org.aisql.bigdata.base.gojira.monster

import org.aisql.bigdata.base.gojira.enum.MonsterType.MonsterType
import org.aisql.bigdata.base.gojira.model.{ClassModel, FieldMeta}
import org.aisql.bigdata.base.util.DateUtil
import org.slf4j.LoggerFactory

/**
  * Author: xiaohei
  * Date: 2019/9/15
  * Email: xiaohei.info@gmail.com
  * Host: xiaohei.info
  */
abstract class Ancestor(whoami: String) extends Serializable {

  protected val logger = LoggerFactory.getLogger(this.getClass)

  logger.info(s"${this.getClass.getSimpleName} init")

  //固定成员属性
  //与 framework 包保持一致
  protected val frameworkPackage = "org.aisql.bigdata.base.framework"

  /**
    * 作者与日期信息
    **/
  protected val author: String =
    s"""
       |/**
       |  * Author: $whoami
       |  * Date: ${DateUtil.currTime}
       |  * CreateBy: @${this.getClass.getSimpleName}
       |  *
       |  */
      """.stripMargin

  //外部传入的属性,引用时注意必须在设置之后使用

  /**
    * 表所在的数据库名
    **/
  var database: String = ""

  /**
    * 由表名解析而来的基础类名
    **/
  var baseClass: String = ""

  /**
    * 表结构的元数据信息列表
    **/
  var fieldMeta: Seq[FieldMeta] = Seq.empty[FieldMeta]

  //强制需要子类实现的属性

  /**
    * 生产器类型,Bean、Dao、Serivce等
    **/
  val monsterType: MonsterType

  val rootClass: String

  val implPkg: String

  //子类需要实现,将会在调用构造函数时初始化一次,可以在后续的init过程修改

  /**
    * 开头包名
    **/
  protected var pkgName: String

  /**
    * import列表
    **/
  protected var impPkgs: String

  protected var beanClassName: String

  protected var classHeader: String

  /**
    * 类型生成类
    **/
  protected var classModel: ClassModel

  /**
    * 类初始化设置
    *
    * 设置classModel相关字段
    **/
  def init(): Unit

  override def toString: String = classModel.toString
}
