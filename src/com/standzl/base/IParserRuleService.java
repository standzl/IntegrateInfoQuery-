package com.standzl.base;

import java.util.List;

public interface IParserRuleService {

		public List<? extends ISystemEntity> parserHtmlTextToArray(String htmlText);
		
		public ISystemEntity parserHtmlTextToEntity(String htmlText);
}
