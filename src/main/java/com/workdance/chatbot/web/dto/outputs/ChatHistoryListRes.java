package com.workdance.chatbot.web.dto.outputs;

import lombok.Data;


/**
 * assistant
 * :
 * "**答案：**\n\nBakery 是一个单站式产品演示平台，旨在提升产品迭代中的研发协作效能。它适用于需求分析、开发联调、测试验收、产品预览等多种场景。\n\nBakery 由 Bakery UI 和 Bakery Web 两部分组成，基于雨燕研发体系进行了功能拓展，覆盖了前端研发的各个环节，并在产品迭代演进过程中持续为各岗位参与者提供帮助。\n\n** Bakery UI 的主要特点如下：**\n\n* 多场景适用，适用于开发、联调、提测、视觉验收、回归测试等场景。\n* 低成本接入，根据项目类型选择对应的插件快速接入。\n* 操作成本低，提供强大的 mock 数据管理工具。\n* 场景组管理，支持业务流程发起页和若干有序 RPC 接口或 JSAPI 返回值的集合。\n* 高度还原，提供 Web 版本的 JSAPI 实现，以展示产品界面和交互细节。\n* 版本管理，支持迭代维度的隔离和从雨燕迭代详情页直接跳转至 Bakery Web 站点"
 * chatId
 * :
 * "7a03a6f0-e7ac-414b-8721-bc099c498f1c"
 * deletedAt
 * :
 * null
 * gmtCreate
 * :
 * "2024-03-18T08:10:17.000+00:00"
 * gmtModified
 * :
 * "2024-03-18T08:11:01.000+00:00"
 * id
 * :
 * 100082
 * messageId
 * :
 * "a56caab3-4976-4f40-9e58-fe7074b77688"
 * userMessage
 * :
 * "请介绍下 Bakery"
 */
@Data
public class ChatHistoryListRes {
  public String assistant;
  public String chatId;
  public String gmtCreate;
  public String userMessage;
  public String brainId;
  public String brainName;
}
