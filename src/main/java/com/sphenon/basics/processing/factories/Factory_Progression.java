package com.sphenon.basics.processing.factories;

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
import com.sphenon.basics.exception.*;
import com.sphenon.basics.notification.*;
import com.sphenon.basics.customary.*;
import com.sphenon.basics.expression.*;
import com.sphenon.basics.validation.returncodes.*;

import com.sphenon.basics.processing.*;
import com.sphenon.basics.processing.classes.*;

public class Factory_Progression {
    private boolean  is_valid;

    private String   progression;
    private Progress progress;
    private Float    percent;

    protected RegularExpression specification = new RegularExpression("^(?:(NO_PROGRESS|POSSIBLY_PROGRESS|SOME_PROGRESS|SKIPPED|COMPLETED)/?)?(?:([0-9]+(?:\\.[0-9]+)?)%)?$");

    public void setProgression(CallContext context, String progression) {
        this.progression = progression;
        String[] m = null;
        this.progress = null;
        this.percent  = null;
        this.is_valid = false;
        if (    progression == null
             || progression.isEmpty()
             || (m = specification.tryGetMatches(context, progression)) == null) {
        } else {
            if (m[0] == null || m[0].isEmpty()) {
                this.progress = Progress.valueOf(m[0]);
            }
            if (m[1] != null && m[1].isEmpty() == false) {
                this.percent = Float.parseFloat(m[1]);
            }
            if (this.progress == null && this.percent != null) {
                if      (this.percent <=   0.0) { this.progress = Progress.NO_PROGRESS; }
                else if (this.percent >= 100.0) { this.progress = Progress.COMPLETED; }
                else                            { this.progress = Progress.SOME_PROGRESS; }
            }
            this.is_valid = true;
        }
    }

    public void validateProgression(CallContext context) throws ValidationFailure {
        if (! this.is_valid) {
            ValidationFailure.createAndThrow (context, "Invalid progression parameter for Factory_Progression, got '%(got)', expected 'NO_PROGRESS|POSSIBLY_PROGRESS|SOME_PROGRESS|SKIPPED|COMPLETED / ###.#%'", "got", this.progression);
            throw (ValidationFailure) null; // compiler insists
        }
    }

    public Progression create (CallContext context) {
        try {
            this.validateProgression(context);
        } catch (ValidationFailure vf) {
            CustomaryContext.create((Context)context).throwPreConditionViolation(context, vf, "At least one factory parameter is invalid");
            throw (ExceptionPreConditionViolation) null; // compiler insists
        }

        return new Class_Progression(context, this.progress, this.percent);
    }
}
