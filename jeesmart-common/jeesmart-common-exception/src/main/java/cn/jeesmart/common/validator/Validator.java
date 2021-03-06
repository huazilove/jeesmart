package cn.jeesmart.common.validator;


import cn.jeesmart.common.exception.SystemException;
import cn.jeesmart.common.utils.StringHelper;
import cn.jeesmart.common.utils.ValidateUtils;

/**
 * 验证器
 * 
 * @author Joe
 */
public enum Validator {

	/**
	 * 非空验证
	 */
	NOT_BLANK {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (StringHelper.isBlank(value)) {
				throw new SystemException(name + "不能为空！");
			}
		}
	},
	/**
	 * 中文验证
	 */
	CHINESE {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isChinese(value)) {
				throw new SystemException(name + "必须为中文！");
			}
		}
	},
	/**
	 * 整数验证
	 */
	INT {
		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isInteger(value)) {
				throw new SystemException(name + "必须为整数！");
			}
		}
	},
	/**
	 * 日期验证
	 */
	DATE {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isDate(value)) {
				throw new SystemException(name + "格式不对！");
			}
		}
	},
	/**
	 * 身份号验证
	 */
	IDNO {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isIdNo(value)) {
				throw new SystemException(name + "不符合身份证号格式，请检查！");
			}
		}
	},
	IP {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isIp(value)) {
				throw new SystemException(name + "不符合IP地址格式，请检查！");
			}
		}
	},
	/**
	 * 邮件验证
	 */
	EMAIL {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isEmail(value)) {
				throw new SystemException(name + "格式不正确，请检查！");
			}
		}
	},
	/**
	 * 手机验证
	 */
	MOBILE {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isMobile(value)) {
				throw new SystemException(name + "格式不正确，请检查！");
			}

		}
	},
	/**
	 * 密码验证
	 */
	PASSWORD {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isPassword(value)) {
				throw new SystemException(name + "格式不正确，请检查！");
			}
		}
	},
	/**
	 * 姓名验证
	 */
	PERSONNAME {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isPersonName(value)) {
				throw new SystemException(name + "格式不正确，请检查！");
			}

		}
	},
	/**
	 * 用户名验证
	 */
	USERNAME {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isUsername(value)) {
				throw new SystemException(name + "格式不正确，请检查！");
			}

		}
	},

	/**
	 * 金额格式
	 */
	MONEY {

		@Override
		public void validate(String name, String value) throws SystemException {
			if (!ValidateUtils.isMoney(value)) {
				throw new SystemException(name + "格式不正确，请检查！");
			}
		}
	};
	/**
	 * 参数校验
	 * 
	 * @param name
	 *            参数的中文名称
	 * @param value
	 *            参数的值
	 * @throws Exception
	 */
	public abstract void validate(String name, String value) throws SystemException;

	/**
	 * 根据验证器的名称获取验证器
	 * 
	 * @param v
	 * @return
	 */
	public static Validator getValidator(Validator v) {
		for (Validator validator : values()) {
			if (validator == v) {
				return validator;
			}
		}
		return null;
	}
}
