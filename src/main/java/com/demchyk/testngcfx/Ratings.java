package com.demchyk.testngcfx;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by volod on 31-Mar-17.
 */
public class Ratings
{
    private String Source;

    private String Value;

    public String getSource ()
    {
        return Source;
    }

    public void setSource (String Source)
    {
        this.Source = Source;
    }

    public String getValue ()
    {
        return Value;
    }

    public void setValue (String Value)
    {
        this.Value = Value;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [Source = "+Source+", Value = "+Value+"]";
    }
}
