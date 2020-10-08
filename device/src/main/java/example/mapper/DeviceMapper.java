package example.mapper;

import example.entity.Device;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.common.Mapper;

@org.apache.ibatis.annotations.Mapper
public interface DeviceMapper extends Mapper<Device> {

}
