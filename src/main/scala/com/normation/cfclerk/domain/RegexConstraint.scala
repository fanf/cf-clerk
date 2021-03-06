/*
*************************************************************************************
* Copyright 2011 Normation SAS
*************************************************************************************
*
* This program is free software: you can redistribute it and/or modify
* it under the terms of the GNU Affero General Public License as
* published by the Free Software Foundation, either version 3 of the
* License, or (at your option) any later version.
*
* In accordance with the terms of section 7 (7. Additional Terms.) of
* the GNU Affero GPL v3, the copyright holders add the following
* Additional permissions:
* Notwithstanding to the terms of section 5 (5. Conveying Modified Source
* Versions) and 6 (6. Conveying Non-Source Forms.) of the GNU Affero GPL v3
* licence, when you create a Related Module, this Related Module is
* not considered as a part of the work and may be distributed under the
* license agreement of your choice.
* A "Related Module" means a set of sources files including their
* documentation that, without modification of the Source Code, enables
* supplementary functions or services in addition to those offered by
* the Software.
*
* This program is distributed in the hope that it will be useful,
* but WITHOUT ANY WARRANTY; without even the implied warranty of
* MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
* GNU Affero General Public License for more details.
*
* You should have received a copy of the GNU Affero General Public License
* along with this program. If not, see <http://www.gnu.org/licenses/agpl.html>.
*
*************************************************************************************
*/

package com.normation.cfclerk.domain
import com.normation.utils.HashcodeCaching

case class RegexConstraint(pattern: String = "", errorMsg: String = "") extends HashcodeCaching {

  /* throw a ConstraintException if the value doesn't match the pattern */
  def check(varValue: String, varName: String) =
    if (pattern != "" && !varValue.matches(pattern))
      throw new ConstraintException("%s%s".format(
        "Please modify " + varName + " to match the requested format",
        if (errorMsg != "") " : " + errorMsg else ""))

}

object RegexConstraint {
  // (?i) : case insensitive
  val mail = """(?i)\b[A-Z0-9._%+-]+@[A-Z0-9.-]+\.[A-Z]{2,4}\b"""

  val ip = """\b(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.){3}""" +
    """(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\b"""

}

object MailRegex extends RegexConstraint(RegexConstraint.mail, "invalid format for the given email (valid example : pat@example.com)")
object IpRegex extends RegexConstraint(RegexConstraint.ip, "invalid format for the given ip (should be xxx.xxx.xxx.xxx, where xxx is a number from 0-255)")
