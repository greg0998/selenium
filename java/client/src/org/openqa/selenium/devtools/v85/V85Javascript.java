// Licensed to the Software Freedom Conservancy (SFC) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The SFC licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package org.openqa.selenium.devtools.v85;

import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.Event;
import org.openqa.selenium.devtools.idealized.Javascript;
import org.openqa.selenium.devtools.v85.page.Page;
import org.openqa.selenium.devtools.v85.page.model.ScriptIdentifier;
import org.openqa.selenium.devtools.v85.runtime.Runtime;
import org.openqa.selenium.devtools.v85.runtime.model.BindingCalled;

import java.util.Optional;

public class V85Javascript extends Javascript<ScriptIdentifier, BindingCalled> {

  public V85Javascript(DevTools devtools) {
    super(devtools);
  }

  @Override
  protected Command<Void> enableRuntime() {
    return Runtime.enable();
  }

  @Override
  protected Command<Void> addJsBinding(String scriptName) {
    return Runtime.addBinding(scriptName, Optional.empty());
  }

  @Override
  protected Command<Void> enablePage() {
    return Page.enable();
  }

  @Override
  protected Command<ScriptIdentifier> addScriptToEvaluateOnNewDocument(String script) {
    return Page.addScriptToEvaluateOnNewDocument(script, Optional.empty());
  }

  @Override
  protected Event<BindingCalled> bindingCalledEvent() {
    return Runtime.bindingCalled();
  }

  @Override
  protected String extractPayload(BindingCalled event) {
    return event.getPayload();
  }
}
