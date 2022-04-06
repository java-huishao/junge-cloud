``@OperateDataRecord(actionName = "解锁了客户:", id = "#id", module = ModuleConstant.customer, operateType = 12)``
``@ModifyDataRecord(name = "更新产品上架状态",id = "#entity.id",module = ModuleConstant.product)``
``@AddDataRecord(actionName = "新增了产品:",id = "#entity.id",moduleName = "#entity.name",module = ModuleConstant.product)``
``@SysLogAction(action = ActionEnum.ADD, targetObject = "#entity.userName", detail = "'添加了用户:'+#entity.userName")``