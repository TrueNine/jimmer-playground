package org.example.entities

import org.babyfish.jimmer.jackson.JsonConverter
import org.babyfish.jimmer.jackson.LongToStringConverter
import org.babyfish.jimmer.sql.Entity
import org.babyfish.jimmer.sql.GeneratedValue
import org.babyfish.jimmer.sql.GenerationType
import org.babyfish.jimmer.sql.Id
import org.babyfish.jimmer.sql.OneToOne

@Entity
interface ATable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonConverter(LongToStringConverter::class)
  val id: Long

  val otherVariable: String

  @OneToOne
  val b: BTable
}
