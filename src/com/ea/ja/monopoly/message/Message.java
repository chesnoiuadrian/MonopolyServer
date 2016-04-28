package com.ea.ja.monopoly.message;

import java.io.Serializable;


public class Message implements Serializable{

    private int code;
    private Object serializableObject;
    private static final long serialVersionUID = 42L;

    /**
     *
     * @param code integer between 0 and NaN
     * @param serializableObject serializable object, respecting the contract of codes
     * @throws InvalidRequstedCode if the requested code is invalid
     */
    public Message(int code, Object serializableObject) throws InvalidRequstedCode {
        setCode(code);
        setSerializableObject(serializableObject);
    }

    /**
     *
     * @return type code of the message
     */
    public int getCode() {
        return code;
    }

    /**
     * setter for the code
     * @param code code of the message
     * @throws InvalidRequstedCode if the requsted code is invalid
     */
    private void setCode(int code) throws InvalidRequstedCode {
        if(code < 0)
            throw new InvalidRequstedCode("Requsted code is invalid!");
        this.code = code;
    }

    /**
     *
     * @return the requsted object
     */
    public Object getSerializableObject() {
        return serializableObject;
    }

    /**
     * setter of the object
     * @param serializableObject the object
     */
    private void setSerializableObject(Object serializableObject) {
        this.serializableObject = serializableObject;
    }
}
