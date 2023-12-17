package bondagehub.domain.model.attachment

import bondagehub.domain.model.*
import bondagehub.domain.model.member.Pass


/**
 * 関係性を持っているか。
 */
class IsAttach(value: Boolean) : SomeValueObject<IsAttach, Boolean>(value) {

	companion object {
		/**
		 * 紐付け追加
		 */
		fun attach(): IsAttach = IsAttach(true)

		/**
		 * 紐付け解除(デフォルト)
		 */
		fun detach(): IsAttach = IsAttach(false)
	}
}
