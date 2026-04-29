PLATFORM = "lemans"
PBT_BUILD_DATE = "260502"

require common.inc

SRC_URI[camxlib.sha256sum]     = "43119d312121b4e4b5588326df6568bf487aa530c6cc26409eefb5a53d1e04ff"
SRC_URI[camx.sha256sum]        = "07107a3275df5cd6077cecdf2116598181d2d63934c9ecb4191ad20377caabd6"
SRC_URI[chicdk.sha256sum]      = "7daaaf8601355137ec0323948710ec962fb4d8e5ffe73393971628d5ebf3b966"
SRC_URI[camxcommon.sha256sum]  = "ed04306b348d141b4c02038860d908eadf6fa4fdf2776f572ec776357ac115ff"

DEPENDS += "${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'qcom-adreno virtual/libopencl1', '', d)}"

do_install:append() {
    # Copy json only when /etc folder exists in ${S}
    if [ -d "${S}/etc" ]; then
        install -d ${D}${sysconfdir}/camera/test/NHX/
        cp -r ${S}/etc/camera/test/NHX/*.json ${D}${sysconfdir}/camera/test/NHX/
    fi
}

RPROVIDES:${PN} = "camxlib-monaco"
PACKAGE_BEFORE_PN += "camx-nhx"
RRECOMMENDS:${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'opencl', 'virtual-opencl-icd', '', d)}"

FILES:camx-nhx = "\
    ${bindir}/nhx.sh \
    ${sysconfdir}/camera/test/NHX/ \
"

FILES:${PN} += "${@bb.utils.contains('DISTRO_FEATURES', 'opencl', '${libdir}/camx/${PLATFORM}/*.cl ${libdir}/camx/${PLATFORM}/libmctf_cl_program.bin', '', d)}"
