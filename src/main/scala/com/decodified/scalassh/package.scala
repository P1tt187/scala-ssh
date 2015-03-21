/*
 * Copyright (C) 2011 Mathias Doenitz
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.decodified

package object scalassh {
  type Validated[T] = Either[String, T]
  class RichSshClient(sshClient: SshClient) extends SshClient(sshClient.config) with ScpTransferable

  def make[A, U](a: A)(f: A ⇒ U): A = { f(a); a }

  implicit def sshClientToRichClient(sshClient: SshClient) = new RichSshClient(sshClient)
}
