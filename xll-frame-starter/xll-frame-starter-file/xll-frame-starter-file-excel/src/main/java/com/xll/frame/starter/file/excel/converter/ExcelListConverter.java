package com.xll.frame.starter.file.excel.converter;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.text.CharSequenceUtil;
import com.alibaba.excel.converters.Converter;
import com.alibaba.excel.enums.CellDataTypeEnum;
import com.alibaba.excel.metadata.GlobalConfiguration;
import com.alibaba.excel.metadata.data.ReadCellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.metadata.property.ExcelContentProperty;
import com.xll.frame.starter.core.constant.StringConstants;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 功能描述:List 集合转换器 <br>
 * <p>
 *  仅适合 List<基本类型> <=> xxx,xxx 转换
 * </p>
 * @author xuliangliang
 * @since 2025/2/16 20:28
 * @version 1.0.0
 */
@Component
public class ExcelListConverter implements Converter<List> {

    @Override
    public Class supportJavaTypeKey() {
        return List.class;
    }

    @Override
    public CellDataTypeEnum supportExcelTypeKey() {
        return CellDataTypeEnum.STRING;
    }

    @Override
    public List convertToJavaData(ReadCellData<?> cellData,
                                  ExcelContentProperty contentProperty,
                                  GlobalConfiguration globalConfiguration) {
        String stringValue = cellData.getStringValue();
        return CharSequenceUtil.split(stringValue, StringConstants.COMMA);
    }

    @Override
    public WriteCellData<Object> convertToExcelData(List value,
                                                    ExcelContentProperty contentProperty,
                                                    GlobalConfiguration globalConfiguration) {
        WriteCellData<Object> writeCellData = new WriteCellData<>(CollUtil.join(value, StringConstants.COMMA));
        writeCellData.setType(CellDataTypeEnum.STRING);
        return writeCellData;
    }
}
