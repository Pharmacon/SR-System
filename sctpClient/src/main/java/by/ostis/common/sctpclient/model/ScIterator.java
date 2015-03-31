package by.ostis.common.sctpclient.model;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

public class ScIterator implements ScParameter {

    private List<ScParameter> parameters;

    public ScIterator() {

        this.parameters = new ArrayList<>();
    }

    public ScIterator registerParameter(ScParameter param) {

        parameters.add(param);
        return this;
    }
    
    public ScParameter getElement(int index){
        
        return parameters.get(index);
    }
    
    @Override
    public byte[] getBytes() {

        ByteBuffer tempBuffer = ByteBuffer.allocate(getByteSize());
        for (ScParameter parameter : parameters) {
            tempBuffer.put(parameter.getBytes());
        }
        return tempBuffer.array();
    }

    @Override
    public int getByteSize() {

        int byteLenght = 0;
        for (ScParameter scParameter : parameters) {
            byteLenght += scParameter.getByteSize();
        }
        return byteLenght;
    }

}
