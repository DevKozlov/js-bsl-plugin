/*
 * Sonar JavaScript Plugin
 * Copyright (C) 2011 Eriks Nukis and SonarSource
 * dev@sonar.codehaus.org
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this program; if not, write to the Free Software
 * Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02
 */
package org.sonar.javascript.checks;

import com.sonar.sslr.squid.checks.CheckMessagesVerifier;
import org.junit.Test;
import org.sonar.javascript.JavaScriptAstScanner;
import org.sonar.squid.api.SourceFile;

import java.io.File;

public class ExcessiveParameterListCheckTest {

  private ExcessiveParameterListCheck check = new ExcessiveParameterListCheck();

  @Test
  public void defaults() {
    SourceFile file = JavaScriptAstScanner.scanSingleFile(new File("src/test/resources/checks/excessiveParameterList.js"), check);
    CheckMessagesVerifier.verify(file.getCheckMessages())
        .next().atLine(4).withMessage("Function has 8 parameters which is greater than 7 authorized.")
        .noMore();
  }

  @Test
  public void test() {
    check.setMaximumFunctionParameters(8);
    SourceFile file = JavaScriptAstScanner.scanSingleFile(new File("src/test/resources/checks/excessiveParameterList.js"), check);
    CheckMessagesVerifier.verify(file.getCheckMessages())
        .noMore();
  }

}
