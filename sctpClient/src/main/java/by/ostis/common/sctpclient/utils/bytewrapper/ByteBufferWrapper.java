package by.ostis.common.sctpclient.utils.bytewrapper;

import java.nio.ByteBuffer;

//TODO change realization
public abstract class ByteBufferWrapper {

	protected ByteBuffer currentBuffer;

	protected int currentSize;

	public ByteBufferWrapper() {
		this(512);
	}

	public ByteBufferWrapper(int initialSize) {
		currentBuffer = ByteBuffer.allocate(initialSize);
	}

	public ByteBufferWrapper put(byte b) {
		//TODO claculate size;
		currentBuffer.put(b);
;		return this;
	}

	public ByteBufferWrapper put(short s){
		//TODO claculate size;
		currentBuffer.putShort(s);
		return this;
	}

	public ByteBufferWrapper put(int i){
		//TODO claculate size;
		currentBuffer.putInt(i);
		return this;
	}

	public ByteBufferWrapper put(long l){
		//TODO claculate size;
		currentBuffer.putLong(l);
		return this;
	}
	
	public ByteBufferWrapper putString(String string){
		byte[] stringAsBytes = string.getBytes();
		//TODO claculate size;
		currentBuffer.put(stringAsBytes);
		return this;
	}

}
