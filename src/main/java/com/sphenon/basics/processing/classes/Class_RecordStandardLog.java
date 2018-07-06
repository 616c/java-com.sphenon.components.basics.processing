package com.sphenon.basics.processing.classes;

/****************************************************************************
  Copyright 2001-2018 Sphenon GmbH

  Licensed under the Apache License, Version 2.0 (the "License"); you may not
  use this file except in compliance with the License. You may obtain a copy
  of the License at http://www.apache.org/licenses/LICENSE-2.0

  Unless required by applicable law or agreed to in writing, software
  distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
  WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
  License for the specific language governing permissions and limitations
  under the License.
*****************************************************************************/

import com.sphenon.basics.context.*;
import com.sphenon.basics.processing.*;

import com.sphenon.ui.annotations.*;

public class Class_RecordStandardLog implements RecordStandardLog {

    public Class_RecordStandardLog(CallContext context) {
    }

    public Class_RecordStandardLog(CallContext context, String standard_output, String standard_error) {
        this.standard_output = standard_output;
        this.standard_error = standard_error;
    }

    protected String standard_output;

    @UIAttribute(Name="Standard Output")
    public String getStandardOutput (CallContext context) {
        return this.standard_output;
    }

    public void setStandardOutput (CallContext context, String standard_output) {
        this.standard_output = standard_output;
    }

    protected String standard_error;

    @UIAttribute(Name="Standard Error")
    public String getStandardError (CallContext context) {
        return this.standard_error;
    }

    public void setStandardError (CallContext context, String standard_error) {
        this.standard_error = standard_error;
    }

    public String toString() {
        return "Standard Output:\n\n" + this.standard_output + "\n\n" + "Standard Error:\n\n" + this.standard_error + "\n";
    }
}
