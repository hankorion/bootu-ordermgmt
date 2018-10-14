package com.durain.bootu.ordermgmt.order.service.utils;

import com.durain.bootu.ordermgmt.order.service.VO.ResultVO;

public class ResultVOUtil {
	public static ResultVO success(Object object) {
		ResultVO resultVO = new ResultVO();
		resultVO.setCode(0);
		resultVO.setMsg("success");
		resultVO.setData(object);
		return resultVO;
	}
}
