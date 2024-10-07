package com.sphenon.basics.processing.classes;

/****************************************************************************
  Copyright 2001-2024 Sphenon GmbH

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

public class Class_RecordLog implements RecordLog {

    public Class_RecordLog(CallContext context) {
    }

    public Class_RecordLog(CallContext context, String log) {
        this.log = log;
    }

    protected String log;

    @UIAttribute(Name="Log")
    public String getLog (CallContext context) {
        return this.log;
    }

    public void setLog (CallContext context, String log) {
        this.log = log;
    }

    public String toString() {
        return this.log;
    }
}
