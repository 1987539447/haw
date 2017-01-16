package com.bigknow.system.mapper;

import com.bigknow.frame.mapper.IMapper;
import com.bigknow.system.entity.Node;

/**
 * Created by Administrator on 2015/3/20.
 */
public interface NodeMapper extends IMapper<Node> {

    public Node findById4KeyStore(String id);
}
